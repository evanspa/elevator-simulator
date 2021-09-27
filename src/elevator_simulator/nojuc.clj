(ns elevator-simulator.nojuc)

(defn spawn-rider
  [id
   starting-floor
   starting-floor-arrival-timestamp
   dest-floor
   body-weight
   is-rational]
  {:id id
   :starting-floor starting-floor
   :starting-floor-arrival-timestamp starting-floor-arrival-timestamp
   :dest-floor dest-floor
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
        (spawn-rider (.toString (java.util.UUID/randomUUID))
                     starting-floor
                     :now
                     dest-floor
                     (rand-nth body-weight-range)
                     true)))))
