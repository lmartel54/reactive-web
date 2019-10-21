'use strict';

angular.module('replica.services', [])

// .service('ReplicaDatasourceService', [ "$http", function($http) {
//
// var service = {};
//
// service.list = function() {
// return $http.get('monitor/db/list');
// }
//
// service.addOperation = function(table, nombre){
// return $http.post('monitor/db/addOperation/' + table + '/' + nombre);
// }
//	
// service.deleteOperation = function(table){
// return $http.post('monitor/db/'+table+'/delete');
// }
// return service;
// } ])
//
// .service('ProcessAreaService', [ "$http", function($http) {
//
// var service = {};
//
// service.list = function() {
// return $http.get('monitor/process/list');
// }
//
// service.fireEvent = function(action, process) {
// return $http.post('process/' + action + '/' + process.processId);
// }
//
// service.fireEventAll = function(action) {
// return $http.post('process/' + action + '/all');
// }
//
// return service;
// } ])
//
// .service('WorkingAreaService', [ "$http", function($http) {
//
// var service = {};
//
// service.get = function() {
// return $http.get('monitor/working/area');
// }
//
// service.getDetail = function(objectId) {
// return $http.get('monitor/working/area/' + objectId);
// }
//
// service.drop = function() {
// return $http.post('monitor/working/area/drop');
// }
//
// return service;
// } ])
//
// .service('RejectAreaService', [ "$http", function($http) {
//
// var service = {};
//
// service.get = function() {
// return $http.get('monitor/reject/area');
// }
//
// service.getDetail = function(objectId) {
// return $http.get('monitor/reject/area/' + objectId);
// }
//
// service.drop = function() {
// return $http.post('monitor/reject/area/drop');
// }
//
// return service;
// } ])

.service('EntityService', [ "$q", function($q) {

	var service = {};

	service.get = function(url) {
		
		var deferred = $q.defer();
		var promise = deferred.promise;
		
		var eventSource = new EventSource(url);
		
		eventSource.onmessage = function(e) {
			console.log('Event received => ' + e.data);
			deferred.notify(JSON.parse(e.data));
		}
		eventSource.onerror = function(e) {
			if (eventSource.readyState === 0) {
				console.log('Event stream has been closed by the server.');
				eventSource.close();
				deferred.resolve('All data received!');
			} else {
				deferred.reject(e);
			}
		}
		
		promise.next = function(fn) {
			promise.then(null, null, fn);
	        return promise;
	    }
	    
		return promise;
	}

	return service;
}

]);