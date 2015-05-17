userDashboard.controller("userListController", ['$scope', 'userResource', 'ngDialog', function($scope, userResource, ngDialog){
	$scope.users = userResource.query();
	$scope.openNewForm = function(newUser){
	  $scope.newUser = newUser || {};
      $scope.dialog = ngDialog.open({
        template: 'templates/add_user.html',
        scope: $scope
      });
    };

  $scope.createNewUser = function(newUser){
    var callback = function(){
      $scope.users = userResource.query();
      $scope.dialog.close();
    };
    newUser.id ? userResource.update(newUser, callback) : userResource.save(newUser, callback);
  }

  $scope.deleteUser = function(newUser){
    userResource.delete({},{'id': newUser.id}, function(){
      $scope.users = userResource.query();
    });
  }
}]);