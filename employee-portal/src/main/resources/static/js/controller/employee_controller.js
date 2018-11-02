'use strict';

angular.module('myApp').controller('EmployeeController', ['$scope', 'EmployeeService', function($scope, EmployeeService) {
    var self = this;
    self.employee={id:null,firstname:'',lastname:'',email:''};
    self.employees=[];
      	
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    fetchAllEmployee();
   
    function fetchAllEmployee(){
        EmployeeService.fetchAllEmployee()
            .then(
            function(d) {
                self.employees = d;
            },
            function(errResponse){
                console.error('Error while fetching Employees');
            }
        );
    }

    function createEmployee(employee){
    	EmployeeService.createEmployee(employee)
            .then(
            fetchAllEmployee,
            function(errResponse){
                console.error('Error while creating Employee');
            }
        );
    }

    function updateEmployee(employee, id){
    	EmployeeService.updateEmployee(employee, id)
            .then(
            fetchAllEmployee,
            function(errResponse){
                console.error('Error while updating Employee');
            }
        );
    }

    function deleteEmployee(id){
    	EmployeeService.deleteEmployee(id)
            .then(
            fetchAllEmployee,
            function(errResponse){
                console.error('Error while deleting Employee');
            }
        );
    }

    function submit() {
        if(self.employee.id===null){
            console.log('Saving New Employee', self.employee);
            createEmployee(self.employee);
        }else{
            updateEmployee(self.employee, self.employee.id);
            console.log('Employee updated with id ', self.employee.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.employees.length; i++){
            if(self.employees[i].id === id) {
                self.employee = angular.copy(self.employees[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.employee.id === id) {
            reset();
        }
        deleteEmployee(id);
    }


    function reset(){
        self.employee={id:null,firstname:'',lastname:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
