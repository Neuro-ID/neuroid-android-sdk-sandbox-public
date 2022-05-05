#!/bin/sh

APP_BUILD_GRADLE=app/build.gradle
DATE=$(date "+%s")
# replace versionCode
sed -i "" "s/versionCode *[0-9]*/versionCode ${DATE}/" ${APP_BUILD_GRADLE}

# replace versionName
sed -i "" "s/inhouse/${DATE}/" ${APP_BUILD_GRADLE}