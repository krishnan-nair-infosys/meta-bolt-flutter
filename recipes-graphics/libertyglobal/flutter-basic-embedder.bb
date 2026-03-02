#
# If not stated otherwise in this file or this component's LICENSE file the
# following copyright and licenses apply:
#
# Copyright 2019-2020 Liberty Global B.V.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# Author: Damian Wrobel <dwrobel@ertelnet.rybnik.pl>
#

#require recipes-devtools/flutter/flutter-common.inc

SUMMARY     = "Flutter Wayland embedding launcher"
HOMEPAGE    = "https://github.com/LibertyGlobal/flutter-embedder-wayland"

LICENSE = "BSD-3-Clause"

FLUTTER_LAUNCHER_WAYLAND_SRCBRANCH ?= "develop"
FLUTTER_LAUNCHER_WAYLAND_SRCREV ?= "c560f3168243866e107d71d82c46f1c2dcf02665"

SRC_URI  = "git://github.com/bcatrysse/flutter-embedder-wayland.git;protocol=https;branch=${FLUTTER_LAUNCHER_WAYLAND_SRCBRANCH};rev=${FLUTTER_LAUNCHER_WAYLAND_SRCREV}"

LIC_FILES_CHKSUM = "file://LICENSE;md5=5c812f8f3c95dc6811ef68cb1eef87e5"

S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

TOOLCHAIN = "clang"

DEPENDS = "flutter-engine libxkbcommon wayland-native wayland-protocols extra-cmake-modules virtual/egl"

inherit pkgconfig cmake

require conf/include/flutter-version.inc

do_configure:prepend() {
# determine build type based on what flutter-engine installed
 FLUTTER_RUNTIME_MODES="$(ls ${STAGING_DIR_TARGET}/usr/share/flutter/${FLUTTER_SDK_VERSION}/)"

 for FLUTTER_RUNTIME_MODE in $FLUTTER_RUNTIME_MODES; do
    if [ "${FLUTTER_RUNTIME_MODE}" = "release" ]; then
        break
    elif [ "${FLUTTER_RUNTIME_MODE}" = "jit_release" ]; then
        break
    elif [ "${FLUTTER_RUNTIME_MODE}" = "profile" ]; then
        break
    elif [ "${FLUTTER_RUNTIME_MODE}" = "debug" ]; then
        break
    fi
 done
 FLUTTER_ENGINE_PATH=${STAGING_DIR_TARGET}/usr/share/flutter/${FLUTTER_SDK_VERSION}/${FLUTTER_RUNTIME_MODE}
 echo "Flutter engine path: $FLUTTER_ENGINE_PATH"
 echo "Using Flutter runtime mode: $FLUTTER_RUNTIME_MODE"
}

# we have no flutter-engine.pc need to give these path to cmake as argument
#
EXTRA_OECMAKE:append = " \
 -DFLUTTER_ENGINE_INCLUDE_DIRS=${STAGING_INCDIR} \
 -DFLUTTER_ENGINE_LIBRARY_DIRS=${STAGING_DIR_TARGET}/usr/share/flutter/${FLUTTER_SDK_VERSION}/release/lib \
 -DFLUTTER_ENGINE_LIBRARIES=flutter_engine \
"

FILES_${PN} = "${bindir}"

#do_install:append() {
#    # Canonical provider for Yocto shlib resolver
#    install -d ${D}${libdir}
#    ln -sf ${datadir}/flutter/3.38.3/release/lib/libflutter_engine.so \
#           ${D}${libdir}/libflutter_engine.so
#}

#SHLIBS_DIRS = "${libdir} ${base_libdir}"

#workaround for 3 flutter-engine.so shlibs scan can find for release profile debug
#EXCLUDE_FROM_SHLIBS = "1"
#RDEPENDS:${PN} += "flutter-engine"

python () {
    d.setVar('FLUTTER_SDK_VERSION', get_flutter_sdk_version(d))
}
