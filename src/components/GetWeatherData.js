import axios from 'axios';

const API_KEY = 'jSLG7PhndCZp9dBtSCY5UGFS4dLgXrtHWCe4JURn1K7VE7UDXwRv9xyHgez0UaGVP8L9%2Bv22bAKf9Uy%2BPWrFeQ%3D%3D';
const API_URL = 'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst';

const GetWeatherData = async (x, y) => {
  try {
    const response = await axios.get(API_URL, {
      params: {
        serviceKey: API_KEY,
        numOfRows: 10,
        pageNo: 1,
        dataType: 'JSON',
        base_date: 20231008,
        base_time: 0o600,
        nx: 37.5665,
        ny: 126.9780
        // ...기타 필요한 매개변수 추가
      },
    });

    // API 응답 데이터에서 기온 데이터 추출
    const temperatureData = response.data.response.body.items.item.find((item) => item.category === 'T1H');

    // temperature 속성에 기온 데이터 저장
    const weatherData = {
      temperature: temperatureData ? temperatureData.fcstValue : null
      // 다른 데이터도 필요한 경우 추가
    };
    return weatherData;
  } 
  catch (error) {
    console.error('API 요청 오류:', error);
    throw error;
  }
};

export default GetWeatherData;