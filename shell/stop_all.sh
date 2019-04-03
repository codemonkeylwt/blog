#!/bin/bash

jps |  grep 'blog' | awk '{print $1}' | xargs kill -15