# meta-bolt-flutter

Bitbake meta layer extending the **bolt** distro with recipes allowing to build flutter runtime (flutter engine + embedder) OCI artifact

# Setup and building

See [Setup and building](https://github.com/rdkcentral/meta-bolt-distro/blob/develop/README.md#setup-and-building)
section in the [meta-bolt-distro](https://github.com/rdkcentral/meta-bolt-distro) documentation.

## flutter runtime build instructions

* Download this repository and enter its root directory.
```
git clone https://github.com/rdkcentral/meta-bolt-flutter.git
cd meta-bolt-flutter
```

* Setup the build environment.
```
source setup-environment
```

* Start building the flutter-runtime  image.
```
bitbake flutter-runtime-bolt-image
```
## Building flutter-runtime as bolt package! NOT READY YET

To create Bolt packages for Fluttere, ensure that the base package is available in the package store. Refer to the [building the base bolt package](https://github.com/rdkcentral/meta-bolt-distro?tab=readme-ov-file#building-the-base-bolt-package) section to generate the base package and set up the package store.


Follow the same steps mentioned in the [Cobalt OCI image building instructions](#cobalt-oci-image-building-instructions) chapter to setup and build the Cobalt runtime, but instead of calling `bitbake cobalt-bolt-image`, use the [bolt tool](https://github.com/rdkcentral/bolt-tools/tree/main/bolt) to create bolt packages for Cobalt.

```
bolt make flutter-runtime --install

```

## Running flutter bolt packages on device ! NOT READY yet

To run bolt packages on device, use `bolt push` and `bolt run` as explained in [bolt tool usage](https://github.com/rdkcentral/bolt-tools/tree/main/bolt#usage)

```
bolt push <remote> TBD
```
