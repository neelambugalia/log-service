#!/bin/bash

callApi() {
  API_URL='http://server:9090/api/logs'
  x=1
  while :
  do
    API_CALL=$(curl -X POST $API_URL -H 'Content-Type:application/json' -d "{\"logId\": $x,\"userId\": \"Neelam\",\"eventName\": \"LOGIN\",\"unixTs\": $(date +%s)}")
    echo "$API_CALL"
    x=$(( x + 1 ))
  done
}

callApi
