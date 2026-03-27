import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.*
import com.madhan.core.ui.components.RenderMarkers
import com.madhan.core.ui.repo.getMarkerPositions

@Composable
fun GoogleMapView(mapType: MapType) {
    val markerPositions = getMarkerPositions()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(markerPositions[0], 12f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = com.google.maps.android.compose.MapType.NORMAL)
    ) {
        RenderMarkers(markerPositions)
    }
}



