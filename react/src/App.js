import React, { Component } from "react";
import { Route, Redirect, Switch } from "react-router-dom";
import "./App.css";
import NotFound from "./components/notFound";
import SideBar from "./components/common/sideBar";
import Home from "./components/home";
import Scanner from "./components/scanner";
import AutoTrader from "./components/autotrader";
import TradersRoom from "./components/tradersRoom";
import Learn from "./components/learn";
import NavigationBar from "./components/common/navigationBar";

class App extends Component {
  render() {
    return (
      <React.Fragment>
        <body>
          <div className="container-fluid">
            <div className="row">
              <NavigationBar />
              <SideBar />
              <main
                style={{ marginTop: "50px" }}
                className="col-md-9 ml-sm-auto col-lg-10 px-4"
              >
                <Switch>
                  <Route path="/home" component={Home} />
                  <Route path="/scanners" component={Scanner}></Route>
                  <Route path="/autotrader" component={AutoTrader}></Route>
                  <Route path="/tradersroom" component={TradersRoom}></Route>
                  <Route path="/learn" component={Learn}></Route>
                  <Route path="/not-found" component={NotFound}></Route>
                  <Redirect from="/" exact to="/home" />
                  <Redirect to="/not-found" />
                </Switch>
              </main>
            </div>
          </div>
        </body>
      </React.Fragment>
    );
  }
}

export default App;
