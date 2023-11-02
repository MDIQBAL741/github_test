import axios from 'axios';
import React, { useEffect } from 'react'
import { useParams } from 'react-router-dom'

export default function UpdateSkill() {
    // const[skillsId]=useParams();
    // useEffect(()=>{
    //         axios.get('http://localhost:8083/getbyId/'+skillsId)
    //         .then(res => console.log(res))
    //         .catch(err => console.log(err))
    // },[])
  return (
    <div className='d-flex justify-content-center align-items-center w-100'>
        <form>
            <div>
                <label htmlFor="skill">Skill</label>
                <input type='text' skill='skill' className='form-control' placeholder='Enter Skill'></input>
            </div>
            <button className='btn btn-info'>Update</button>
        </form>
        </div>
  )
}
