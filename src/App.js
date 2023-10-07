import AddSearchBox from "./components/AddSearchBox"
import AddTypeFilter from "./components/AddTypeFilter"
import AddRegionFilter from "./components/AddRegionFilter"
import AddMap from "./components/AddMap"
import "./data/App.css";

function App() {
  return (
    <>
      <div id="AddSearchBox"><AddSearchBox/></div> 
      <AddTypeFilter/>
      &emsp;
      <AddRegionFilter/>
      <AddMap/>
    </>
  );
}

export default App;
