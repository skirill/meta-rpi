SUMMARY = "xrdp - an open source RDP server"
DESCRIPTION = "xrdp provides a graphical login to remote machines using Microsoft \
               Remote Desktop Protocol (RDP). xrdp accepts connections from a \
               variety of RDP clients: FreeRDP, rdesktop, NeutrinoRDP and Microsoft \
               Remote Desktop Client (for Windows, Mac OS, iOS and Android). "
HOMEPAGE = "https://github.com/neutrinolabs/xrdp"
SECTION = "console/network"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=72cfbe4e7bd33a0a1de9630c91195c21"

SRC_URI = "gitsm://github.com/neutrinolabs/xrdp.git \
           file://0001-use-shared-dep-libs.patch \
           file://0002-use-keygen-from-host.patch \
"

SRCREV = "7a7445fefd9a61535881ceedf5ed82febb52d2dc"
PV = "0.9.2+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

REQUIRED_DISTRO_FEATURES = "x11"

DEPENDS = "virtual/libx11 libxfixes libxrandr libpam openssl"

# FILES_${PN} += "${libdir}/xrdp/*.so"

# TODO: how to correctly package versioned .so libs from autobuild? The above FILES_${PN}
# line is not enough. Still xrdp can start so this is not mission critical.
INSANE_SKIP_${PN} = "dev-so"

do_configure_prepend () {
	( cd ${S}; ${S}/bootstrap )
}
