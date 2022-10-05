<template>
    <div>
        <div id="map"></div>
    </div>
</template>
<script>
export default{
    mounted(){  //mounted : dom 구성 후 처리
        if (window.kakao && window.kakao.maps) {
            this.initMap();
            this.displayMarkers();
        } else {
            const script = document.createElement("script");
            /* global kakao */
            script.onload = () => kakao.maps.load(this.initMap);
            script.src ="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=3f57d02e134f1067307cbaec0b42c437&libraries=services";
            document.head.appendChild(script);
        }
    },
    data : function(){
        return{
            map : null,
            markerPositions : [],    //title, latlng
            markers:[]
        }
    },
    methods : {
        initMap(){
            const container = document.getElementById("map");
            const options = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
                level: 5,
            };
            this.map = new kakao.maps.Map(container, options);
        },
        displayMarkers(positions){
            //현재 표시된 마커들 제거
            if(this.markers.length>0){
                this.markers.forEach((item) => {
                    item.setMap(null);
                });
            }
            var positions = [
                {
                    title: '카카오', 
                    latlng: new kakao.maps.LatLng(33.450705, 126.570677)
                },
                {
                    title: '생태연못', 
                    latlng: new kakao.maps.LatLng(33.450936, 126.569477)
                },
                {
                    title: '텃밭', 
                    latlng: new kakao.maps.LatLng(33.450879, 126.569940)
                },
                {
                    title: '근린공원',
                    latlng: new kakao.maps.LatLng(33.451393, 126.570738)
                }
            ];
            // 마커 이미지의 이미지
            var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
            //마커 이미지 크기
            var imageSize = new kakao.maps.Size(24, 35); 
            //마커 생성
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
            
            //마커 표시
            positions.forEach((position)=>{
                const marker = new kakao.maps.Marker({
                    map : this.map,
                    position : position.latlng,
                    title : position.title,
                    image : markerImage
                });
                this.markers.push(marker);
            });

            //지도 이동
            const bounds = positions.reduce(
                (bounds, position) => bounds.extend(position.latlng),
                new kakao.maps.LatLngBounds()
            );
            this.map.setBounds(bounds);
        }
    }
}
</script>
<style>
#map{
   
}
</style>
