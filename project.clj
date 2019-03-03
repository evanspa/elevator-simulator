(defproject elevator-simulator "0.1.0-SNAPSHOT"
  :description "Simulation for a building elevator."
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/core.async "0.4.490"]]
  :repl-options {:init-ns elevator-simulator.core})
