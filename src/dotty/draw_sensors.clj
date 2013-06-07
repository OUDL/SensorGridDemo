(ns dotty.draw-sensors
  (:require [quil.core :as qc])
  (:use [dotty.setup :only [CW HEIGHT NCOLS NROWS RH WIDTH]]))

(def SENSOR_LINE_WIDTH 5.0)

(defn draw-sensor-point
  [x y col row t]
  (let [t (* t 0.1)
        n (qc/noise col row t)
        r (+ 1 (* n 4))]
    (qc/fill 255 255)
    (qc/ellipse x y r r)))

(defn update-sensor-point
  [{:keys [x y] :as sensor} [col row] tick-atom]
  (let [t (* 0.01 @tick-atom)
        t (+ (rand 0.01) t) ;; Make a randomness of points little smoother.
        pos (+ col (* NCOLS row))
        new-x (+ (* (- NCOLS col 1) CW) (* CW (qc/noise pos t)))
        new-y (+ (* row RH) (* RH (qc/noise pos (+ 5.2 t))))]
    (do
      (draw-sensor-point new-x new-y col row @tick-atom)
      (-> sensor
          (assoc-in [:x] new-x)
          (assoc-in [:y] new-y)))))

(defn draw-sensor-points
  [grid-sensors-atom tick-atom]
  (dorun
   (for [col-row (keys @grid-sensors-atom)
         :let [sensor (@grid-sensors-atom col-row)
               updated (update-sensor-point sensor col-row tick-atom)]]
     (swap! grid-sensors-atom #(assoc-in % [col-row] updated)))))

(defn draw-sensor-grid
  [grid-sensors-atom tick-atom]
  (draw-sensor-points grid-sensors-atom tick-atom))
