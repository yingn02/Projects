
const AddRegionWeather = function ({ wdata }) {
    let weatherInfo = [];

    if (wdata && wdata.response && wdata.response.body && wdata.response.body.items && wdata.response.body.items.item) {
        weatherInfo = wdata.response.body.items.item;
        //console.log(weatherInfo[0].fcstValue);

        let dayTmp = weatherInfo[36].fcstValue; //오전 기온
        let nightTmp = weatherInfo[145].fcstValue; //오후 기온

        return (
            <>
                <h3>오전</h3>
                <h3>{dayTmp}도</h3>
                &emsp;
                <h3>오후</h3>
                <h3>{nightTmp}도</h3>
            </>

        );

    }
    else {
        console.log("데이터 로딩중");
    }




}

export default AddRegionWeather;