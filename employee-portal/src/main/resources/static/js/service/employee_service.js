'use strict';

angular.module('myApp').factory('EmployeeService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/employee/';

    var factory = {
        fetchAllEmployee: fetchAllEmployee,
        createEmployee: createEmployee,
        updateEmployee:updateEmployee,
        deleteEmployee:deleteEmployee
    };

    return factory;

    function fetchAllEmployee() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Employees');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createEmployee(employee) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, employee)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Employee');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateEmployee(employee, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, employee)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Employee');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteEmployee(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Employee');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
