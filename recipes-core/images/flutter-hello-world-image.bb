SUMMARY = "test Flutter hello world app image without needing full base rootfs"
IMAGE_FSTYPES = "container oci"

inherit image
inherit image-oci
NO_RECOMMENDATIONS = "1"

IMAGE_LINGUAS = " "

ROOTFS_POSTPROCESS_COMMAND:append = " stub_gpu_libraries;"
#ROOTFS_POSTPROCESS_COMMAND:append = " add_ca_certificate_mount_points;"
# This removes the stub GPU libraries from the rootfs.
# At runtime the real versions of these libraries will be
# provided by the GPU layer.
stub_gpu_libraries() {
    rm -f ${IMAGE_ROOTFS}/usr/lib/libEGL.so
    rm -f ${IMAGE_ROOTFS}/usr/lib/libEGL.so.1

    rm -f ${IMAGE_ROOTFS}/usr/lib/libGLESv1_CM.so
    rm -f ${IMAGE_ROOTFS}/usr/lib/libGLESv1_CM.so.1

    rm -f ${IMAGE_ROOTFS}/usr/lib/libGLESv2.so
    rm -f ${IMAGE_ROOTFS}/usr/lib/libGLESv2.so.2
}



OCI_IMAGE_TAR_OUTPUT=""

IMAGE_CMD:oci:append() {

    if [ -n "$image_name" ]; then
        file_name="$image_name.tar"
    else
        image_name="${IMAGE_NAME}${IMAGE_NAME_SUFFIX}-oci"
        file_name="${IMAGE_NAME}${IMAGE_NAME_SUFFIX}-oci-${OCI_IMAGE_TAG}-${OCI_IMAGE_ARCH}${OCI_IMAGE_SUBARCH:+"-$OCI_IMAGE_SUBARCH"}-linux.oci-image.tar"
    fi

    if [ -z "${OCI_IMAGE_TAR_OUTPUT}" ]; then
        tar --sort=name --format=posix --numeric-owner -cf ${file_name} -C ${image_name} .
    fi

    ln -fs ${file_name} ${IMAGE_BASENAME}.tar
}

IMAGE_INSTALL += "flutter-hello-world"
IMAGE_INSTALL += "flutter-basic-embedder"
IMAGE_INSTALL += "flutter-engine"
