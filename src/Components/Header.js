import React from 'react'

export default function Header() {
  
  return (
      <header>
        
          <div className="abc-logo">
             <img src="abc_logo.png"
             style={{height: "50px"}}/>
          </div>
          <div className="hrc-logo">
             <img src="hrc_logo.jpg"  
             style={{height: "50px"}}/>
          </div>
          <div className="invoice-list">
           Invoice Management Application
          </div>
      </header> 
  )
};
