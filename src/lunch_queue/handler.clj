(ns lunch-queue.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as json-mw]
            [lunch-queue.data :as data]
            [clojure.tools.logging :as log]))

(defroutes api-routes
    (GET "/users" [] { :body { :key "value"} })
    (GET "/restaurants" [] { :body (data/get-restaurants)} )
    (POST "/restaurants" {body :body}
          (data/create-restaurant (select-keys body [:name :address]))
          {:body (data/get-restaurants)}))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (-> (context "/api" [] api-routes)
      (json-mw/wrap-json-body {:keywords? true} )
      (json-mw/wrap-json-response))
  (route/not-found "Not Found"))

(def app app-routes)
