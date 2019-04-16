#!/bin/bash


cur_date="`date +%Y%m%d`"

APP_DIR=/opt/blog/$cur_date

mkdir -p $APP_DIR

JAR_BASE_DIR=/data/jenkins/workspace/blog/

cp $JAR_BASE_DIR/index/target/blog-index.jar $APP_DIR
cp $JAR_BASE_DIR/user/target/blog-user.jar $APP_DIR

cd /opt/blog/

rm -f app

ln -s $APP_DIR app

chmod 777 app/*
