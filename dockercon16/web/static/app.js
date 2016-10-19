"use strict";

var lab = angular.module('lab', []);

lab.controller('LabCtrl', function($scope, $http) {
    $scope.noun1 = {
        word: "",
        hostname: "loading..."
    }
    $scope.noun2 = {
        word: "",
        hostname: "loading..."
    }
    $scope.adjective1 = {
        word: "",
        hostname: "loading..."
    }
    $scope.adjective2 = {
        word: "",
        hostname: "loading..."
    }
    $scope.verb = {
        word: "",
        hostname: "loading..."
    }

    $http.get('/words/noun').success(function(data) {
        $scope.noun1 = data;
    }).error(function() {
        $scope.noun1 = {
            word: "developer",
            hostname: "graceful-error"
        }
    });
    $http.get('/words/adjective').success(function(data) {
        data.word = data.word.charAt(0).toUpperCase() + data.word.substr(1)
        $scope.adjective1 = data;
    }).error(function() {
        $scope.adjective1 = {
            word: "The angry",
            hostname: "graceful-error"
        }
    });
    $http.get('/words/verb').success(function(data) {
        $scope.verb = data;
    }).error(function() {
        $scope.verb = {
            word: "breaks",
            hostname: "graceful-error"
        }
    });
    $http.get('/words/noun').success(function(data) {
        $scope.noun2 = data;
    }).error(function() {
        $scope.noun2 = {
            word: "application",
            hostname: "graceful-error"
        }
    });
    $http.get('/words/adjective').success(function(data) {
        $scope.adjective2 = data;
    }).error(function() {
        $scope.adjective2 = {
            word: "a crazy",
            hostname: "graceful-error"
        }
    });
});
