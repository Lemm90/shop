angular.module('shop', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/shop/api/v1';
    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.shopUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {
        delete $localStorage.shopUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.shopUser) {
            return true;
        } else {
            return false;
        }
    };

    if ($localStorage.shopUser) {
        try {
            let jwt = $localStorage.shopUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.shopUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.shopUser.token;
    }
    $scope.loadProducts = function () {
                $http.get(contextPath + '/products/'
                ).then(function (response) {
                    $scope.productsList = response.data.content;
                });
    };

        $scope.addProduct = function () {
                    $http.post(contextPath + '/products/', $scope.newProducts)
                    .then(function (response) {
                        alert ('Продукт добавлен')
                        $scope.loadProducts;
                    });
        };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changePrice = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_price',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8190/shop-carts/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

        $scope.addProductToCart = function (productId) {
            $http.get('http://localhost:8190/shop-carts/api/v1/cart/add/' + productId).then(function (response) {
                $scope.loadCart();
            });
        }

    $scope.removeProductFromCart = function (productId) {
        console.log('remove from cart product id: ' + productId)
        $http({
            url: 'http://localhost:8190/shop-carts/api/v1/cart/remove',
            method: 'GET',
            params: {
                productId: productId,
            }
        }).then(function (response) {
            $scope.loadCart();
            });
        };

    $scope.clearCart = function (productId) {
        $http.get('http://localhost:8190/shop-carts/api/v1/cart/clear/').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: 'http://localhost:8190/shop-carts/api/v1/cart/change_quantity',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.createOrder = function () {
        $http.post(contextPath + '/orders')
            .then(function (response) {
                $scope.loadProducts;
            });
    };

    $scope.loadProducts();
    $scope.loadCart();
});