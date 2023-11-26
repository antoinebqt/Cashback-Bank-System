#!/bin/bash

function compile_dir()  # $1 is the dir to get it
{
    cd $1
    ./build.sh
    cd ..
}

echo "** Building all"

compile_dir "transaction-service"

compile_dir "cashback-service"

compile_dir "account-service"

compile_dir "affiliated-store-service"

compile_dir "external-bank-mock-service"

compile_dir "external-mid-interpreter-mock-service"

compile_dir "external-carrefour-mock-service"

compile_dir "external-decathlon-mock-service"

compile_dir "external-mastercard-mock-service"

# remove # to add pause in script execution
#read -p "Press any key to continue... "

echo "** Done all"
