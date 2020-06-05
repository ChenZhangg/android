package net.cyclestreets.api.client.geojson

import net.cyclestreets.api.Map
import net.cyclestreets.api.Maps
import net.cyclestreets.api.client.geojson.AbstractObjectFactory.propertyOrDefault
import org.geojson.Feature
import org.geojson.FeatureCollection

class MapsFactory {
    companion object {
        fun toMaps(featureCollection: FeatureCollection): Maps {
            return Maps(
                featureCollection.features
                        .map { f -> toMap(f) }
                        .filter { m -> isBritainOrIreland(m) }
            )
        }

        private fun toMap(feature: Feature): Map {
            return Map(
                    feature.getProperty("id"),
                    feature.getProperty("name"),
                    feature.getProperty("url"),
                    propertyOrDefault(feature, "parent", "")
            )
        }

        private fun isBritainOrIreland(m: Map): Boolean {
            return isBritainOrIreland(m.id)
        }
        private fun isBritainOrIreland(p: String): Boolean {
            return p.contains("great-britain") || p.contains("ireland")
        }
    }
}
