(defproject com.github.clj-kondo/lein-clj-kondo "2024.09.27"
  :description "Lein plugin to run clj-kondo"
  :url "https://clj-kondo.github.io/clj-kondo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :pedantic? :warn
  :dependencies [[clj-kondo/clj-kondo "2024.09.27"]]
  :deploy-repositories [["clojars" {:url "https://clojars.org/repo"
                                    :username :env/clojars_username
                                    :password :env/clojars_password
                                    :sign-releases false}]]
  #_#_:clj-kondo {:fail-level :error})
