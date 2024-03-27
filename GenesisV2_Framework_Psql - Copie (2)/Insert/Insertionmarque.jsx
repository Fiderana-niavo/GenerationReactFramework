import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { insertMarque } from '../service/MarqueService.jsx'; 


const Api_url = [url];

function InsertMarque() {
    const formDataInsert = new FormData();

    const navigate = useNavigate();
    const [formData, setFormData] = useState ({
    });

    const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    

    const handleFormSubmit = async () =>{
              formDataInsert.append('idmarque', formData.idmarque);
      formDataInsert.append('nommarque', formData.nommarque);

        const insert = insertmarque(formDataInsert);

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
                    <h4 className="card-title">Insertion marque</h4>
                      <>
                         
                        <div className="form-group">
                            <label>idmarque</label>
                            <input type="number" className="form-control"
                            name="idmarque"
                            value={formData.idmarque}
                            onChange={handleInputChange}/>
                        </div>

                        <div className="form-group">
                            <label>nommarque</label>
                            <input type="text" className="form-control"
                            name="nommarque"
                            value={formData.nommarque}
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

export default InsertMarque;
