import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { insertVoiture } from '../service/VoitureService.jsx'; 

import { selectMarque } from '../service/MarqueService.jsx'; 


function InsertVoiture() {
    const formDataInsert = new FormData();

    const navigate = useNavigate();
    const [formData, setFormData] = useState ({
    });

    const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    
const[marques,setmarques]=useState([]);
useEffect(() =>{
    const fetchData=async() => {
        const data=await selectMarque();
        setmarques(data);
    };
    fetchData();
},[]);


    const handleFormSubmit = async () =>{
              formDataInsert.append('idvoiture', formData.idvoiture);
      formDataInsert.append('marque', formData.marque);
      formDataInsert.append('nomvoiture', formData.nomvoiture);
      formDataInsert.append('datasortie', formData.datasortie);

        const insert = insertvoiture(formDataInsert);

      if(insert==true){
        navigate("/");
      }else{
        alert(insert);
        console.log(insert)
      }
    }

    return (
    <div className="container-scroller">
      <div className="container-fluid page-body-wrapper">
        <Header /> 
        <Sidebar /> 
        <div className="main-panel">
          <div className="content-wrapper">
            <div className="row">
              <div className="col-md-6 grid-margin stretch-card mx-auto">
                <div className="card ">
                  <div className="card-body">
                    <h4 className="card-title">Insertion voiture</h4>
                      <>
                         
                        <div className="form-group">
                            <label>idvoiture</label>
                            <input type="number" className="form-control"
                            name="idvoiture"
                            value={formData.idvoiture}
                            onChange={handleInputChange}/>
                        </div>

<select name={marque}>
    {
        marques.map((marque,setmarque)=>(
            <option key={index} value={marque.idmarque}>{marque.idmarque}</option>
        ) )
    }
</select>

                        <div className="form-group">
                            <label>nomvoiture</label>
                            <input type="text" className="form-control"
                            name="nomvoiture"
                            value={formData.nomvoiture}
                            onChange={handleInputChange}/>
                        </div>

                        <div className="form-group">
                            <label>datasortie</label>
                            <input type="date" className="form-control"
                            name="datasortie"
                            value={formData.datasortie}
                            onChange={handleInputChange}/>
                        </div>

                        <button onClick={handleFormSubmit} className="btn btn-primary mr-2">
                          Inserer
                        </button>
                        </>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default InsertVoiture;
