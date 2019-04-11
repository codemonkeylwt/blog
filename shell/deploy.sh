#!/bin/bash

MODULE=$1

VERSION=$2

FILENAME=""

path=$(cd ..;pwd -P)

repostiorypath="../blog-repository/ink/casual/blog-$MODULE"

if [[ -d $repostiorypath ]]; then
    count=$(cd $repostiorypath;ls -l |grep "^d"|wc -l)
    if [[ "$count" -ge "3" ]]
    then
        $(cd ../blog-repository/ink/casual/blog-$MODULE;ls -ltr | grep -v 'total' | grep "^d" | awk '{print $9}' | head -n 1 | xargs rm -rf)
    fi
fi

BASEPATH=${path:2}

cd ../$MODULE/target/

if [[ "$MODULE" == "common" ]]
then
    FILENAME=blog-$MODULE.jar
else
    FILENAME=blog-$MODULE-provider.jar
fi

mvn deploy:deploy-file -DgroupId=ink.casual -DartifactId=blog-$MODULE -Dversion=$VERSION -Dpackaging=jar -Dfile=$FILENAME -Durl=file:$BASEPATH/blog-repository -DrepositoryId=snapshots

read -p "Press any key to continue." var