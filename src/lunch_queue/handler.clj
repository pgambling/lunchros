(ns lunch-queue.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes api-routes
    (GET "/users" [] "testing"))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (context "/api" [] api-routes)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
