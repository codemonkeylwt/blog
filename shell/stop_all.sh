#!/bin/bash

pids=$(jps |  grep 'blog' | awk '{print $1}')

if [[ $pids ]];then
        jps |  grep 'blog' | awk '{print $1}' | xargs kill -15
    else
        echo "service is not running"
fi