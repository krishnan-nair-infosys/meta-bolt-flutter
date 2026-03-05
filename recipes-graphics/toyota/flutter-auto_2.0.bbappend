#our meta-bolt-distro sets GCC as default toolchain for compilation
#compilation of flutter-auto.bb with GCC is succeeding in our environment
#However flutter-auto.bb seems to prefer to use Clang toolchain for its own compilation as also required for flutter-engine
#however in our setup I see that despite settings in flutter-auto.bb like TOOLCHAIN = "clang" and other lang/llvm settings it compiles with GCC toolchain
#to fix and force Clang compilation for this recipe only use either 
#a)
#CC = "clang"
#CXX = "clang++"
#or 
#b)
#inherit clang


# In case we use GCC as toolchain iso clang/llvm
# below flag is clang-only warning flag
#CXXFLAGS:remove = "-Wno-deprecated-literal-operator"

# --- Remove the plugins repo from fetch sources ---
SRC_URI:remove = "gitsm://github.com/toyota-connected/ivi-homescreen-plugins.git;protocol=https;branch=v2.0;name=plugins;destsuffix=${S}/ivi-homescreen-plugins"

# --- Remove the plugins license file reference (it lives inside the removed repo) ---
LIC_FILES_CHKSUM:remove = "file://${S}/ivi-homescreen-plugins/LICENSE;md5=39ae29158ce710399736340c60147314"

# --- Neutralize SRCREV metadata that refers to 'name=plugins' ---
# The base recipe appends '_plugins' to SRCREV_FORMAT and sets SRCREV_plugins.
# Clear the plugins SRCREV to avoid confusing bitbake's fetch logic.
SRCREV_plugins = ""
# Strip the '_plugins' suffix from SRCREV_FORMAT by removing the appended piece.
# NOTE: You must match EXACTLY what the .bb added ("_plugins")
SRCREV_FORMAT:remove = "_plugins"

# --- Ensure CMake does not expect the plugins tree ---
# Option A (recommended): Disable plugins generically via provided PACKAGECONFIG flag
PACKAGECONFIG:append = " disable-plugins"

# Option B (alternative): If you want to keep plugins enabled but with no external plugins tree,
# then neutralize the PLUGINS_DIR cmake define. Uncomment ONE of the below:
# 1) Remove the argument entirely:
#EXTRA_OECMAKE:remove = "-D PLUGINS_DIR=${S}/ivi-homescreen-plugins/plugins"
# 2) Or set it to a harmless non-existing path:
#EXTRA_OECMAKE:append = " -D PLUGINS_DIR="

# --- Optional: clean up any per-name checksum or other named URI attributes ---
SRC_URI[plugins.sha256sum] = ""
SRC_URI[plugins.md5sum] = ""

# Override PACKAGECONFIG for flutter-auto_2.0
# Keep only the minimal set needed for now to get up and running, we can expand afterwards.

PACKAGECONFIG = "\
    backend-wayland-egl \
    client-xdg \
"
EXTRA_OECMAKE += "\
    -DBUILD_IVI_HOME_SCREEN_PLUGINS=OFF \
    -DBUILD_PLUGINS=OFF \
    -DENABLE_DBUS=OFF \
"
