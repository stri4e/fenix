#!/bin/bash

function build_all_services() {
  if [ -f build-settings ]; then

    cat build-settings
    services=$(jq -r '.services[]' build-settings)

    for service in ${services[*]}
      do
          printf "RUN CLEAN AND BUILD SERVICE: %s\n" $service
          cd $service
          if [ -f gradlew ]; then
              ./gradlew clean && ./gradlew bootJar && cd ..
          fi
          if [ -f build-golang-app-script.sh ]; then
              /bin/bash build-golang-app-script.sh && cd ..
          fi
          printf "\n"
    done

  fi
}

function build_service() {
  service="$1"
  printf "RUN CLEAN AND BUILD SERVICE: %s\n" $service
  cd $service
  if [ -f gradlew ]; then
    ./gradlew clean && ./gradlew bootJar && cd ..
  fi
  if [ -f build-golang-app-script.sh ]; then
     /bin/bash build-golang-app-script.sh && cd ..
  fi
  printf "\n"
}

function start_all() {
  build_all_services
  dc_file="$1"
  echo $dc_file
  docker-compose -f ${dc_file} up --build --force-recreate -d
}

function stop_all() {
  dc_file="$1"
  docker-compose -f ${dc_file} stop
  docker-compose -f ${dc_file} down --rmi all
}

function restart() {
    stop_all
    start_all
}

function start() {
  dc_file="$1"
  service="$2"
  printf "RUN CLEAN AND BUILD SERVICE: %s\n" $service
  cd $service
  if [ -f gradlew ]; then
    ./gradlew clean && ./gradlew bootJar && cd ..
  fi
  if [ -f build-golang-app-script.sh ]; then
     /bin/bash build-golang-app-script.sh && cd ..
  fi
  printf "\n"
  docker-compose -f ${dc_file} up -d --force-recreate --no-deps --build $service
  docker-compose -f ${dc_main} logs -f
}

function stop() {
  dc_file="$1"
  service="$2"
  docker-compose -f ${dc_file} stop $service
  docker-compose -f ${dc_file} rm -f $service
}

action="build_all_services"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval ${action}
