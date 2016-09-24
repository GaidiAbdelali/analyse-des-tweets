
										


										<form role="form" name="match" action="/addMatch" method="post" >

											<div class="form-group">
                                           		 <label>first Team</label>
                                          		 <input name="firstTeam" class="form-control" placeholder="Enter team">
                                       	    </div>

                                       	    <div class="form-group">
                                           		 <label>Second Team </label>
                                           		 <input name="secondTeam" class="form-control" placeholder="Enter tem">
                                            </div>

                                            <div class="form-group">
                                            	<label>leagues</label>
                                            	<select name="league" class="form-control">
                                            		<option value="">- Choose League -</option>
	                                                <option value="1">Premier League (England)</option>
                                                    <option value="2">Primera Division (Spain)</option>
                                                    <option value="3">Bundesliga (Germany)</option>
                                                   <option value="4">Serie A (Italy)</option>
                                                   <option value="4">Ligue 1 (France)</option>
                                                </select>
                                       	 	</div>

                                       	 	 <button type="submit" class="btn btn-primary">Submit Button</button>
                                       		 <button type="reset" class="btn btn-success">Reset Button</button>

                                        </form>		 