$(function(){
    $("#gridContainer").dxDataGrid({
        dataSource: employees,
        selection: {
            mode: "multiple"
        },
        "export": {
            enabled: true,
            fileName: "Plaintes",
            allowExportSelectedData: true
        },
        groupPanel: {
            visible: true
        },
        columns: [
            {
                dataField: "Prefix",
                caption: "ID Plainte",
                width: 60
            }, "FirstName",
            "LastName", 
            "City",
            "State", {
                dataField: "Position",
                width: 130
            }, {
                dataField: "BirthDate",
                dataType: "date",
                width: 100
            }, {
                dataField: "HireDate",
                dataType: "date",
                width: 100
            }     
        ]
    });
});