import React, { useEffect, useState } from 'react';
import axios from 'axios';
import MainRankingTable from './components/mainRankingTable'
function App() {
  const [hidata, setHello] = useState('')
 
  useEffect(() => {
    axios.get('http://localhost:8085/api/hello')
      .then(response => setHello(response.data))
      .catch(error => console.log(error))
  }, []);
 
  return (
    <div>
      <MainRankingTable/>
    </div>
  );
}
 
export default App;
