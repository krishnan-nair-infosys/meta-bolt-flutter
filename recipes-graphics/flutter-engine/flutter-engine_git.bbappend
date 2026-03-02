do_install:append() {
    # Canonical provider for Yocto shlib resolver
    install -d ${D}${libdir}
    ln -sf ${datadir}/flutter/${FLUTTER_SDK_VERSION}/release/lib/libflutter_engine.so \
           ${D}${libdir}/libflutter_engine.so
}

# Tell Yocto that this file belongs to the main runtime package
FILES:${PN} += "${libdir}/libflutter_engine.so"

# Avoid QA warnings (because libflutter_engine.so is unversioned)
# Need symlink from /usr/lib/libflutter_engine.so,  allow unversioned .so symlink in main package iso -dev package
INSANE_SKIP:${PN} += "dev-so"


