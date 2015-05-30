var app = angular.module('internzaApp', []);

app.config(function($interpolateProvider){
    $interpolateProvider.startSymbol('{[').endSymbol(']}');
});

app.controller('internzaAppController', function($scope,$http,$location){


  //===================================================
  // APP INIT
  //===================================================

  $scope.appInit = function() {

    $scope.hello = "HELLO";
  }

  //===================================================
  // USER PROFILE PAGE
  //===================================================
  $scope.userProfileInit = function() {

    $scope.provinceid = "select";
    $scope.cities = [];

    $scope.userProfileWatches();

  }

  $scope.userProfileWatches = function() {

    $scope.$watch("provinceid", function() {

      if ($scope.provinceid != null && $scope.provinceid != "select") {

        $scope.requestCitiesUrl = "/" + $scope.provinceid + "/getcities";

        $http.get($scope.requestCitiesUrl)
          .success(function(resp){
            $scope.cities = resp;
        });

      }



    })
  }

  //===================================================



});
