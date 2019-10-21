'use strict'

var replicaApp = angular.module('replicaApp', [ 'ui.bootstrap', 'replica.controllers', 'replica.services' ]);

//replicaApp.filter('utc', [ function() {
//	return function(date) {
//		if (date) {
//			if (angular.isNumber(date)) {
//				date = new Date(date);
//			}
//			return new Date(date.getUTCFullYear(), date.getUTCMonth(), date
//					.getUTCDate(), date.getUTCHours(), date.getUTCMinutes(), date
//					.getUTCSeconds(), date.getUTCMilliseconds());
//		}
//		return null;
//	}
//} ]);
