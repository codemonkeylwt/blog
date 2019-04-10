#!/usr/bin/env bash

MODULE=$1

VERSION=$2

FILENAME=""

cd ../$MODULE/target/

if [[ "$MODULE" == "common" ]]
then
    FILENAME=blog-$MODULE.jar
else
    FILENAME=blog-$MODULE-provider.jar
fi

mvn deploy:deploy-file -DgroupId=ink.casual -DartifactId=blog-$MODULE -Dversion=$VERSION -Dpackaging=jar -Dfile=$FILENAME -Durl=file:blog-repository -DrepositoryId=snapshots

read -p "Press any key to continue." var