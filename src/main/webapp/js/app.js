// Copyright (c) Cybernetix Inc - 2013

//-----------------------------------------------------------------------------------------------------------------------------------------
(function() {

var app = angular.module('galaxy', []);

//-----------------------------------------------------------------------------------------------------------------------------------------
app.controller('LoginController', [ '$http', function($http) {

	var ctrl = this;
	ctrl.userid = '';
	ctrl.password = '';
	ctrl.submitted = false;

	ctrl.login = function(isValid) {

		ctrl.submitted = true;

		if (!isValid)
			return;

		var credentials = {
			'userid' : ctrl.userid,
			'password' : ctrl.password
		};

		$http({
			method: 'POST',
			url: 'login',
			data: $.param(credentials),
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' }

		}).success(
			function(data, status, headers, config) {
				window.location = 'main.html';
			})
	};
}]);

//-----------------------------------------------------------------------------------------------------------------------------------------
app.controller('NavigationController', [ '$http', function($http) {

	var ctrl = this;
	ctrl.navIndex = 0;
	ctrl.user = null;

	ctrl.load = function() {

		$http({
			method: 'GET',
			url: 'api/v1/user/me'

		})
		.success(
			function(data, status, headers, config) {
				ctrl.user = data;
			});
	};

	ctrl.load();

	ctrl.isGalaxyList = function() {
		return ctrl.navIndex == 0;
	};

	ctrl.gotoGalaxyList = function() {
		ctrl.navIndex = 0;
	};

	ctrl.isPlanetList = function() {
		return ctrl.navIndex == 1;
	};

	ctrl.gotoPlanetList = function() {
		ctrl.navIndex = 1;
	};

	ctrl.isCompanyList = function() {
		return ctrl.navIndex == 2;
	};

	ctrl.gotoCompanyList = function() {
		ctrl.navIndex = 2;
	};

	ctrl.isUserList = function() {
		return ctrl.navIndex == 3;
	};

	ctrl.gotoUserList = function() {
		ctrl.navIndex = 3;
	};
}]);

//-----------------------------------------------------------------------------------------------------------------------------------------
app.controller('GalaxyListController', [ '$http', function($http) {

	var ctrl = this;
	ctrl.data = [];

	ctrl.load = function() {

		$http({
			method: 'GET',
			url: 'api/v1/galaxy'

		})
		.success(
			function(data, status, headers, config) {
				ctrl.data = data;
			});
	};

	ctrl.load();
}]);

//-----------------------------------------------------------------------------------------------------------------------------------------
app.controller('PlanetListController', [ '$http', function($http) {

	var ctrl = this;
	ctrl.data = [];

	ctrl.load = function() {

		$http({
			method: 'GET',
			url: 'api/v1/planet'

		})
		.success(
			function(data, status, headers, config) {
				ctrl.data = data;
			});
	};

	ctrl.load();
}]);

//-----------------------------------------------------------------------------------------------------------------------------------------
app.controller('CompanyListController', [ '$http', function($http) {

	var ctrl = this;
	ctrl.data = [];

	ctrl.load = function() {

		$http({
			method: 'GET',
			url: 'api/v1/company'

		})
		.success(
			function(data, status, headers, config) {
				ctrl.data = data;
			});
	};

	ctrl.load();
}]);

//-----------------------------------------------------------------------------------------------------------------------------------------
app.controller('UserListController', [ '$http', function($http) {

	var ctrl = this;
	ctrl.data = [];

	ctrl.load = function() {

		$http({
			method: 'GET',
			url: 'api/v1/user'

		})
		.success(
			function(data, status, headers, config) {
				ctrl.data = data;
			});
	};

	ctrl.load();
}]);

})();
