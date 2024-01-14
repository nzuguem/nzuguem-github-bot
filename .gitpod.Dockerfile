FROM gitpod/workspace-full

USER gitpod

# Make builder RUN use /bin/bash instead of /bin/sh
# https://github.com/moby/moby/issues/7281
SHELL ["/bin/bash", "-c"]

# Install SDKs
RUN <<EOF
set -e

. /home/gitpod/.sdkman/bin/sdkman-init.sh

echo "sdkman_auto_answer=true" >> "$SDKMAN_DIR"/etc/config
echo "sdkman_auto_env=true" >> "$SDKMAN_DIR"/etc/config

## Install JDKs
sdk install java 21.0.1-graal
sdk default java 21.0.1-graal

## Install Quarkus CLI
sdk install quarkus
EOF