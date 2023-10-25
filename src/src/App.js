import MainSearch from "./components/MainSearch"
//install: antd

/*
<MainSearch/>
1. 검색어, 필터1, 필터2가 바뀔때마다 <MainSearch/>가 값을 받음
2. <MainSearch/>는 값을 토대로 URL 생성하고, API에게 요청, API로 부터 응답받은 값을 <AddCards/>에 넘긴다
3. <AddCards>는 map 함수를 이용하여 <AddCard>컴포넌트 여러개를 나열, title과 addr1을 넘겨준다
*/

function App() {
  return (
    <>
      <MainSearch/>
    </>
  );
}

export default App;
