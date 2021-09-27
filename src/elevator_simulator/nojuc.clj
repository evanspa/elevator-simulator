(ns elevator-simulator.nojuc)

(defn make-rider
  [id
   starting-floor
   starting-floor-arrival-timestamp
   destination-floor
   body-weight
   is-rational]
  {:id id
   :starting-floor starting-floor
   :starting-floor-arrival-timestamp starting-floor-arrival-timestamp
   :destination-floor destination-floor
   :has-summoned-elevator false
   :elevator-entry-timestamp nil
   :elevator-id nil
   :num-stops 0
   :delivered-to-destation-floor-timestamp nil
   :body-weight body-weight
   :is-rational is-rational})

(defn make-dest-matrix
  [num-floors]
  (let [floor-range (map inc (range num-floors))]
    (for [start-floor floor-range
          dest-floor floor-range
          :when (not= start-floor dest-floor)]
      [start-floor dest-floor])))

(defn make-rider-spawner
  [num-floors
   body-min-weight
   body-max-weight
   is-rational]
  (let [dest-matrix (make-dest-matrix num-floors)
        body-weight-range (range body-min-weight (inc body-max-weight))]
    (fn []
      (let [[starting-floor dest-floor] (rand-nth dest-matrix)]
        (make-rider (.toString (java.util.UUID/randomUUID))
                    starting-floor
                    :now
                    dest-floor
                    (rand-nth body-weight-range)
                    true)))))

(defn make-building
  [num-floors elevators]
  {:num-floors num-floors
   :elevators elevators})

(defn make-elevator
  [id max-weight-capacity]
  {:id id
   :max-weight-capacity max-weight-capacity})

(defn make-elevator-system
  [num-floors
   elevators
   rider-body-min-weight
   rider-body-max-weight
   duration-of-simulation-ms]
  (fn []
    (let [system (atom {:keep-running true})
          rider-spawner (make-rider-spawner num-floors
                                            rider-body-min-weight
                                            rider-body-max-weight
                                            true)]

      ;; this thread will just go and spawn riders every 3 seconds
      (.start
       (Thread.
        (fn []
          (loop [make-another true]

            ;;
            (when make-another
              (.start
               (Thread.
                (fn []
                  (let [new-rider (rider-spawner)]
                    (println new-rider))))))

            (Thread/sleep 3000)
            (recur (:keep-running @system))))))

      ;; put the system thread to sleep for the duration of the simulation
      (Thread/sleep duration-of-simulation-ms)
      (swap! system assoc :keep-running false))


    ;; TODO - instead of abrubtly terminating, tell the rider-spawner to stop
    ;; and when all existing riders have been delivered, then end the simulation

    (println "Simulation ended.")))
