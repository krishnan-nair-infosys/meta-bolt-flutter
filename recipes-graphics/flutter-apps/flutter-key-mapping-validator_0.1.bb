# Key Mapping Validator Flutter App
#
SUMMARY = "key_mapping_validator"
DESCRIPTION = "Flutter key mapping validation app for RDK devices"
AUTHOR = "RDK"
HOMEPAGE = "None"
BUGTRACKER = "None"
SECTION = "graphics"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=421eabdde8ad31134feff02518f91a30"

inherit externalsrc
EXTERNALSRC = "/workspace/key_mapping_validator"
S = "/workspace/key_mapping_validator"

PUBSPEC_APPNAME = "key_mapping_validator"
FLUTTER_APPLICATION_INSTALL_SUFFIX = "flutter-key-mapping-validator"
PUBSPEC_IGNORE_LOCKFILE = "1"
FLUTTER_APPLICATION_PATH = "."

inherit flutter-app

do_compile[network] = "1"
export HOME = "${WORKDIR}"

do_install:append() {
    for FLUTTER_RUNTIME_MODE in $(ls ${STAGING_DIR_TARGET}${datadir}/flutter/${FLUTTER_SDK_VERSION}); do
        install -d ${D}${FLUTTER_INSTALL_DIR}/${FLUTTER_SDK_VERSION}/${FLUTTER_RUNTIME_MODE}
        cat > ${D}${FLUTTER_INSTALL_DIR}/${FLUTTER_SDK_VERSION}/${FLUTTER_RUNTIME_MODE}/config.toml << 'TOMLEOF'
[view]
width = 1920
height = 1080
pixel_ratio = 1.0
fullscreen = true
TOMLEOF
    done
}
