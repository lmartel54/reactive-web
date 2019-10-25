'use strict';

angular.module('replica.controllers', [])

// .controller('ReplicaDatasourceController', ["$scope", "$rootScope",
// "ReplicaDatasourceService",
// function($scope, $rootScope, ReplicaDatasourceService) {
//
// console.log('datasource controller is up...');
//
// $scope.tables = undefined;
//		
// $scope.refresh = function() {
// ReplicaDatasourceService.list().then(function(value) {
// $scope.tables = value.data.tables;
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//		
// $scope.addOperation = function(table, nombre) {
// ReplicaDatasourceService.addOperation(table, nombre).then(function() {
// $scope.refresh();
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//
// $scope.deleteOperation = function(table){
// ReplicaDatasourceService.deleteOperation(table).then(function() {
// $scope.refresh();
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//		
// $scope.refresh();
// }]
// )
// .controller('ProcessAreaController', ["$scope", "$rootScope",
// "ProcessAreaService",
// function($scope, $rootScope, ProcessAreaService) {
//
// console.log('process area controller is up...');
//
// $scope.processes = undefined;
//
// $scope.refresh = function() {
// ProcessAreaService.list().then(function(value) {
// $scope.processes = value.data;
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//
// $scope.fireEvent = function(action, process) {
// ProcessAreaService.fireEvent(action, process).then(function() {
// $scope.refresh();
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//
// $scope.fireEventAll = function(action) {
// ProcessAreaService.fireEventAll(action).then(function() {
// $scope.refresh();
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//
// var drop = function(){
// ProcessAreaService.drop().then(function() {
// $scope.refresh();
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//
// $scope.refresh();
// }]
// )
// .controller('WorkingAreaController', ["$scope", "$rootScope",
// "WorkingAreaService",
// function($scope, $rootScope, WorkingAreaService) {
//
// console.log('working area controller is up...');
//
// $scope.processes = undefined;
// $scope.processDetail = undefined;
//
// $scope.refresh = function() {
// WorkingAreaService.get().then(function(value) {
// $scope.processes = value.data;
// $scope.changeSelection(($scope.processes.length > 0) ? 0 : -1);
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//
// var getDetail = function(processDetail) {
// WorkingAreaService.getDetail(processDetail.id).then(function(value) {
// $scope.processDetail = value.data;
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//
// $scope.changeSelection = function(index) {
// $scope.selectedRow = index;
// if (index === -1)
// $scope.processDetail = undefined;
// else
// getDetail($scope.processes[index]);
// };
//
// $scope.drop = function(){
// WorkingAreaService.drop().then(function() {
// $scope.refresh();
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//		
// $scope.refresh();
// }]
// )
// .controller('RejectAreaController', ["$scope", "$rootScope",
// "RejectAreaService",
// function($scope, $rootScope, RejectAreaService) {
//
// console.log('reject area controller is up...');
//
// $scope.errors = undefined;
// $scope.errorDetail = undefined;
//
// $scope.refresh = function() {
// RejectAreaService.get().then(function(value) {
// $scope.errors = value.data;
// $scope.changeSelection(($scope.errors.length > 0) ? 0 : -1);
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//
// var getDetail = function(error) {
// RejectAreaService.getDetail(error.id).then(function(value) {
// $scope.errorDetail = value.data;
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//
// $scope.changeSelection = function(index) {
// $scope.selectedRow = index;
// if (index === -1)
// $scope.errorDetail = undefined;
// else
// getDetail($scope.errors[index]);
// };
//
// $scope.drop = function(){
// RejectAreaService.drop().then(function() {
// $scope.refresh();
// }, function(reason) {
// console.log("error occured");
// }, function(value) {
// console.log("no callback");
// });
// };
//		
// $scope.refresh();
// }]
// )
.controller('InvoiceGasController', [ "$scope", "EntityService",
		function($scope, entityService) {

			console.log('invoiceGas controller is up...');

			$scope.invoices = [];

///////// pagination			
			$scope.pagination = {
				currentPage : 1
			}
			 $scope.viewby = 3;
//			  $scope.totalItems = $scope.invoices.length();
			  $scope.currentPage = 4;
			  $scope.itemsPerPage = $scope.viewby;
			  $scope.maxSize = 2; //Number of pager buttons to show

			  $scope.setPage = function (pageNo) {
				console.log('SetPage changed to: ' + pageNo);
			    $scope.currentPage = pageNo;
			  };

			  $scope.pageChanged = function() {
			    console.log('Page changed to: ' + $scope.currentPage);
			  };

			$scope.setItemsPerPage = function(num) {
			  $scope.itemsPerPage = num;
			  $scope.currentPage = 1; //reset to first page
			}

//			  $scope.$watch("invoices", function() {
//				  $scope.pagination.totalItems = $scope.invoices;
//				  });
			
///////// pagination
			
			// $scope.errors = undefined;
			// $scope.errorDetail = undefined;

			$scope.refresh = function() {

				entityService.get('/api/invoice/gas').next(function(invoice) {
					$scope.invoices.push(invoice);
					$scope.pagination.totalItems = $scope.invoices.length;
				});
			};

			$scope.refresh();
		} ]);
