<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Employee Detail</title>
<style>
.firstname.ng-valid {
	background-color: lightgreen;
}

.firstname.ng-dirty.ng-invalid-required {
	background-color: red;
}

.firstname.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.lastname.ng-valid {
	background-color: lightgreen;
}

.lastname.ng-dirty.ng-invalid-required {
	background-color: red;
}

.lastname.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.email.ng-valid {
	background-color: lightgreen;
}

.email.ng-dirty.ng-invalid-required {
	background-color: red;
}

.email.ng-dirty.ng-invalid-email {
	background-color: yellow;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/app.css">
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container"
		ng-controller="EmployeeController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Employee Registration Form </span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.employee.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">First Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.employee.firstname"
									name="fname" class="firstname form-control input-sm"
									placeholder="Enter your first name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.fname.$error.required">This is a required field</span> 
									<span ng-show="myForm.fname.$error.minlength">Minimum length required is 3</span> 
									<span ng-show="myForm.fname.$invalid">This field is invalid </span>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Last Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.employee.lastname"
									name="lname" class="lastname form-control input-sm"
									placeholder="Enter your last name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.lname.$error.required">This is a required field</span> 
									<span ng-show="myForm.lname.$error.minlength">Minimum length required is 3</span> 
									<span ng-show="myForm.lname.$invalid">This field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Email</label>
							<div class="col-md-7">
								<input type="email" ng-model="ctrl.employee.email" name="email"
									class="email form-control input-sm"
									placeholder="Enter your Email" required />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.email.$error.required">This is a required field</span> 
									<span ng-show="myForm.email.$invalid">This field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.employee.id ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Employees</span> <span
					class="lead floatRight">Search: <input
					ng-model="searchText"></span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID.</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Status</th>
							<th width="20%"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.employees | filter:searchText">
							<td><span ng-bind="u.id"></span></td>
							<td><span ng-bind="u.firstname"></span></td>
							<td><span ng-bind="u.lastname"></span></td>
							<td><span ng-bind="u.email"></span></td>
							<td><span ng-bind="u.status"></span></td>
							<td>
								<button type="button" ng-click="ctrl.edit(u.id)"
									class="btn btn-success custom-width">Edit</button>
								<button type="button" ng-click="ctrl.remove(u.id)"
									class="btn btn-danger custom-width">Remove</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Company Website List</span> 
			</div>
			<div ng-controller="MyController">
			<div class="tablecontainer">
				<table class="table table-hover">
						<tbody ui-sortable ng-model="items">
							<tr ng-repeat="item in items">
								<td>{{ item }}</td>
							</tr>
						</tbody>
				</table>
			</div>
			</div>
		</div>
		
	</div>

	<script src="http://cdn.jsdelivr.net/g/jquery@1,jquery.ui@1.10%28jquery.ui.core.min.js+jquery.ui.widget.min.js+jquery.ui.mouse.min.js+jquery.ui.sortable.min.js%29,angularjs@1.2,angular.ui-sortable"></script>
	<script src="/js/app.js"></script>
	<script src="/js/service/employee_service.js"></script>
	<script src="/js/controller/employee_controller.js"></script>
	<script src="/js/drag.js"></script>

</body>
</html>