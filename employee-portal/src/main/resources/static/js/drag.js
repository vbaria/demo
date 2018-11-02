angular.module('myApp').controller("MyController", function($scope) {
  $scope.items = ["Company.com", "CompanyA.com", "CompanyB.com", "CompanyC.com"];

  $scope.sortableOptions = {
    update: function(e, ui) { 
        console.log(e);
    },
    axis: 'x'
  };
});