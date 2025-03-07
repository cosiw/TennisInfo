import React from "react";

import { DataGrid } from "@mui/x-data-grid";
import { Box, CircularProgress } from "@mui/material";

const columns = [
  { field: "id", headerName: "ID", width: 90 },
  { field: "name", headerName: "Name", width: 150 },
  { field: "rank", headerName: "Rank", width: 110 },
];

const DataTable = () => {
    const [rows, setRows] = useState([]);
    const [loading, setLoading] = useState(true);
  
    useEffect(() => {
      fetch("https://example.com/api/tennis-rankings") // 여기에 실제 API 주소 입력
        .then((response) => response.json())
        .then((data) => {
          setRows(data);
          setLoading(false);
        })
        .catch((error) => console.error("Error fetching data:", error));
    }, []);
  
    return (
      <Box sx={{ height: 400, width: "100%" }}>
        {loading ? (
          <CircularProgress />
        ) : (
          <DataGrid
            rows={rows}
            columns={columns}
            pageSize={5}
            checkboxSelection
            disableSelectionOnClick
          />
        )}
      </Box>
    );
  };

export default MainRankingTable
