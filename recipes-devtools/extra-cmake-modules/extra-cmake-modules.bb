SUMMARY = "Extra modules and scripts for CMake"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING-CMAKE-SCRIPTS;md5=54c7042be62e169199200bc6477f04d1"

PV = "5.76.0+gitr${SRCPV}"
SRCREV = "2135cbdfa6da743f32f3d03b0661313caecc7b16"

SRC_URI = " \
    git://github.com/KDE/extra-cmake-modules;protocol=https;branch=master \
"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DBUILD_TESTING=off"

inherit cmake


# --- Packaging: make sure all ECM bits go into -dev
FILES:${PN}-dev += " \
    ${datadir}/ECM \
    ${libdir}/cmake/ECM \
"

# (Optional) If the main runtime package does not install anything,
# explicitly allow it to be empty to keep QA happy:
ALLOW_EMPTY:${PN} = "1"

#previously
#FILES_${PN}-dev += "${datadir}/ECM"


