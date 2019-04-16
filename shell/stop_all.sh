#!/bin/bash

pids=$(jps |  grep 'blog' | awk '{print $1}')

if [[ $pids ]];then
        jps |  grep 'blog' | awk '{print $1}' | xargs kill -9
    else
        echo "service is not running"
fi