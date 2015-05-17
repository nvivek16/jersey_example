userDashboard.factory('userResource', ['$resource', function($resource){
  return $resource('rest/users/:userId', {userId: '@id'}, {
      'update': { method:'PUT' }
  });
}]);