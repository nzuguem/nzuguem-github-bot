#!/usr/bin/env sh

GITHUB_APP_ID=$1
GITHUB_APP_WEBHOOK_SECRET=$2
GITHUB_APP_PRIVATE_KEY_FILE=$3

set -e

aws ssm put-parameter --name /github-app/id --value "$GITHUB_APP_ID" --type String --overwrite > /dev/null 2>&1
aws ssm put-parameter --name /github-app/webhook-secret --value "$GITHUB_APP_WEBHOOK_SECRET" --type String --overwrite > /dev/null 2>&1
aws ssm put-parameter --name /github-app/private-key --value "$(cat $GITHUB_APP_PRIVATE_KEY_FILE)" --type String --overwrite > /dev/null 2>&1