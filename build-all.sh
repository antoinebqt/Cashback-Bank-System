#!/bin/bash

function compile_dir()  # $1 is the dir to get it
{
    cd $1
    ./build.sh
    cd ..
}

echo "** Building all"

#compile_dir "bank-service"
#
#compile_dir "cashback-service"

compile_dir "external-bank-mock-service"

# remove # to add pause in script execution
#read -p "Press any key to continue... "

echo "** Done all"
